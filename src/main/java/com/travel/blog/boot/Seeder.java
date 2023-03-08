package com.travel.blog.boot;

import com.travel.blog.models.Categories;
import com.travel.blog.models.Travel;
import com.travel.blog.repositories.CategoriesRepository;
import com.travel.blog.repositories.TravelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Seeder implements CommandLineRunner {
    private TravelRepository travelRepository;
    private CategoriesRepository categoriesRepository;

    public Seeder(TravelRepository travelRepository , CategoriesRepository categoriesRepository) {
        this.travelRepository = travelRepository;
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        travelRepository.deleteAll();

        //Create travel
        //Create comment
        //Create category

        Date date = new Date();

        Categories category = new Categories(
                "Romantic"
        );

        Categories category2 = new Categories(
                "Adventure"
        );

        categoriesRepository.save(category);

        Travel travel = new Travel(
                "Travel to Paris",
                "Paris is the capital and most populous city of France, with an area of 105 square kilometres (41 square miles) and a population of 2,206,488. Since the 17th century, Paris has been one of Europe's major centres of finance, diplomacy, commerce, fashion, science and arts. The City of Paris is the centre and seat of government of the Île-de-France, or Paris Region, which has an estimated official 2019 population of 12,213,364, or about 18 percent of the population of France. The Paris Region had a GDP of €709 billion in 2017. According to the Economist Intelligence Unit Worldwide Cost of Living Survey in 2018, Paris was the second most expensive city in the world, after Singapore, and ahead of Zürich, Hong Kong, Oslo and Geneva. Another source ranked Paris as most expensive, on a par with Singapore and Hong Kong, in 2018 and 2019. The city is a global centre for art, fashion, gastronomy and culture. Its 19th-century cityscape is crisscrossed by wide boulevards and the River Seine. Beyond such landmarks as the Eiffel Tower and the 12th-century, Gothic Notre-Dame cathedral, the city is known for its cafe culture and designer boutiques along the Rue du Faubourg Saint-Honoré.",
                "https://t3.gstatic.com/licensed-image?q=tbn:ANd9GcQ1oS-DeKDIgvicoSyoD8KKoIAinTTDeC6VO7erBHEsAggFjaZYZ6YP1HkFahtlKTb_",
                date,
                "France",
                category
        );

        Travel travel2 = new Travel(
                "Travel to Italy",
                "Italy, officially the Italian Republic, is a unitary parliamentary republic in Europe. Located in the heart of the Mediterranean Sea, Italy shares open land borders with France, Switzerland, Austria, Slovenia, San Marino, and Vatican City. Italy covers an area of 301,340 km2 (116,350 sq mi) and has a largely temperate seasonal and Mediterranean climate. With around 60 million inhabitants, Italy is the third-most populous member state of the European Union. The country's capital, Rome, is the largest city and metropolitan area in Italy and the capital of the European Union. Italy is a founding member of the United Nations, the World Trade Organization, the G7, the G20, the North Atlantic Treaty Organization, the Organisation for Economic Co-operation and Development, the Council of Europe and the Group of Eight. Italy is also a member of the Schengen Area, the Eurozone, the OECD, the OSCE, the WTO, the G8+5, the North Atlantic Cooperation Council, the Union for the Mediterranean, the Council of the Baltic Sea States, the Community of Mediterranean States, the Community of Sant'Egidio and the Union for the Mediterranean. Italy is the eighth-most globalised country in the world measured by the Globalisation and World Rankings and the third-most globalised country in Europe.",
                "https://saltaconmigo.com/blog/wp-content/uploads/2014/07/Italia-Roma-Colosseo.jpg",
                date,
                "Italy",
                category2
        );

        travelRepository.save(travel);
        travelRepository.save(travel2);
    }


}
