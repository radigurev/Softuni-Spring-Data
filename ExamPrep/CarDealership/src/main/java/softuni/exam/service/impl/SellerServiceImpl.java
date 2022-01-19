package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.Xml.SellerImportDto;
import softuni.exam.models.dtos.Xml.SellerRootDto;
import softuni.exam.models.entity.Rating;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {
    private static final String SELLER_PATH = "src/main/resources/files/xml/sellers.xml";
    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    public SellerServiceImpl(SellerRepository sellerRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return String.join("", Files.readAllLines(Path.of(SELLER_PATH)));
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        SellerRootDto sellerRootDto = this.xmlParser.parseXml(SellerRootDto.class, SELLER_PATH);
        for (SellerImportDto sellerDto : sellerRootDto.getSellersDto()) {
            try {
                Rating rating = Rating.valueOf(sellerDto.getRating());
            } catch (Exception e) {
                sb.append("Invalid seller").append(System.lineSeparator());
                continue;
            }
            Optional<Seller> byEmail = this.sellerRepository.findByEmail(sellerDto.getEmail());
            if (this.validationUtil.isValid(sellerDto) && byEmail.isEmpty()) {
                Seller seller = this.modelMapper.map(sellerDto, Seller.class);
                this.sellerRepository.saveAndFlush(seller);
                sb.append(String.format("Successfully import seller %s - %s", seller.getLastName(), seller.getEmail()))
                        .append(System.lineSeparator());
            } else {
                sb.append("Invalid seller").append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
