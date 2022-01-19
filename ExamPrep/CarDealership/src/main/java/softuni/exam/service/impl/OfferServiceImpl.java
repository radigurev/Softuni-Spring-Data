package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.Xml.OffersImportDto;
import softuni.exam.models.dtos.Xml.OffersRootDto;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.Offer;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;

@Service
public class OfferServiceImpl implements OfferService {

    private final static String OFFERS_PATH="src/main/resources/files/xml/offers.xml";

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final CarRepository carRepository;
    private final SellerRepository sellerRepository;
    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil, CarRepository carRepository, SellerRepository sellerRepository) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.carRepository = carRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count()>0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(OFFERS_PATH)));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder sb=new StringBuilder();
        OffersRootDto offersRootDto = this.xmlParser.parseXml(OffersRootDto.class, OFFERS_PATH);
        for (OffersImportDto offersImportDto : offersRootDto.getOffersDto()) {System.out.println();
        if(this.validationUtil.isValid(offersImportDto)){
            Offer offer=this.modelMapper.map(offersImportDto,Offer.class);
            Car car=this.carRepository.findById(offersImportDto.getCar().getId()).get();
            Seller seller=this.sellerRepository.findById(offersImportDto.getSeller().getId()).get();

            offer.setCar(car);
            offer.setSeller(seller);
            offer.setPictures(new HashSet<>(car.getPictures()));

            this.offerRepository.saveAndFlush(offer);
            sb.append(String.format("Successfully import offer %s - %s",offer.getAddedOn(),offer.isHasGoldStatus()))
                    .append(System.lineSeparator());
        }else
            sb.append("Invalid offer").append(System.lineSeparator());
        }
        return sb.toString();
    }
}
