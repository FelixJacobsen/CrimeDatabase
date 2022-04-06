package se.iths.crimedatabase.util;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import se.iths.crimedatabase.repository.*;

@Component
public class ExampleData implements ApplicationRunner {

    private AddressRepository addressRepository;
    private VictimRepository victimRepository;
    private CriminalRepository criminalRepository;
    private CrimeRepository crimeRepository;
    private CategoryRepository categoryRepository;

    public ExampleData(AddressRepository addressRepository, VictimRepository victimRepository, CriminalRepository criminalRepository, CrimeRepository crimeRepository, CategoryRepository categoryRepository) {
        this.addressRepository = addressRepository;
        this.victimRepository = victimRepository;
        this.criminalRepository = criminalRepository;
        this.crimeRepository = crimeRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        
    }
}
