package com.travel.blog.boot;

import com.travel.blog.models.Categories;
import com.travel.blog.models.Comment;
import com.travel.blog.models.Travel;
import com.travel.blog.models.User;
import com.travel.blog.repositories.CategoriesRepository;
import com.travel.blog.repositories.CommentRepository;
import com.travel.blog.repositories.TravelRepository;
import com.travel.blog.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Seeder implements CommandLineRunner {
    private final TravelRepository travelRepository;
    private final CategoriesRepository categoriesRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public Seeder(TravelRepository travelRepository , CategoriesRepository categoriesRepository,
                  CommentRepository commentRepository, UserRepository userRepository) {
        this.travelRepository = travelRepository;
        this.categoriesRepository = categoriesRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        travelRepository.deleteAll();
        categoriesRepository.deleteAll();
        commentRepository.deleteAll();
        userRepository.deleteAll();

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

        Categories category3 = new Categories(
                "Family"
        );

        categoriesRepository.save(category);
        categoriesRepository.save(category2);
        categoriesRepository.save(category3);

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

        Travel travel3 = new Travel(
                "Travel to Spain",
                "Spain, officially the Kingdom of Spain, is a country in Southwestern Europe with some pockets of territory across the Strait of Gibraltar and the Atlantic Ocean. Its continental European territory is situated on the Iberian Peninsula. Its territory also includes two archipelagos: the Canary Islands off the coast of North Africa, and the Balearic Islands in the Mediterranean Sea. The African enclaves of Ceuta, Melilla, and Peñón de Vélez de la Gomera make Spain the only European country to have a physical border with an African country (Morocco). Several small islands in the Alboran Sea are also part of Spanish territory. The country's mainland is bordered to the south and east by the Mediterranean Sea except for a small land boundary with Gibraltar; to the north and northeast by France, Andorra, and the Bay of Biscay; and to the west and northwest by Portugal and the Atlantic Ocean respectively. Spain is the largest country in Southern Europe and the European Union by area, and the second-most populous after France. With an estimated population of over 47 million, Spain is the fourth-most populous EU member state and the seventh-most populous country in Europe. The country's capital and largest city is Madrid; other major urban areas include Barcelona, Valencia, Seville, Bilbao, and Málaga.",
                "https://i.natgeofe.com/k/e800ca90-2b5b-4dad-b4d7-b67a48c96c91/spain-madrid.jpg?w=636&h=358",
                date,
                "Madrid",
                category3
        );

        travelRepository.save(travel);
        travelRepository.save(travel2);
        travelRepository.save(travel3);

        User user = new User(
                "John",
                "Doe",
                "123456789"
        );

        User user2 = new User(
                "Jane",
                "Carter",
                "123456789"
        );

        userRepository.save(user);
        userRepository.save(user2);

        Comment comment = new Comment(
                "This is a comment",
                "john@john.com",
                "John",
                travel,
                user
        );

        Comment comment2 = new Comment(
                "I enjoyed this travel",
                "jane@jane.com",
                "Jane",
                travel,
                user2
        );

        Comment comment3 = new Comment(
                "It was very disappointing",
                "john@john.com",
                "John",
                travel2,
                user
        );

        commentRepository.save(comment);
        commentRepository.save(comment2);
        commentRepository.save(comment3);
    }


}
