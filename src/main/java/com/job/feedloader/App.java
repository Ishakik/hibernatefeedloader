package com.job.feedloader;

import com.job.feedloader.builders.UserBuilder;
import com.job.feedloader.exceptions.FeedBuilderException;
import com.job.feedloader.exceptions.FeedParserException;
import com.job.feedloader.fileparsers.CustomFileParser;
import com.job.feedloader.fileparsers.impl.CSVParser;
import com.job.feedloader.models.MasterData;
import com.job.feedloader.models.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Feed loader gets the data from Feed Files, does some basic validations & inserts
 * into database using Hibernate ORM (driver to use is microsoft sql)
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CustomFileParser<MasterData> fileParser = new CSVParser();
        List<MasterData> masterData = new ArrayList<>();
        try {
            masterData.addAll(fileParser.parse("Daily_Feed.csv"));
        } catch (FeedParserException e) {
            System.out.println(e.getMessage());
        }

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory factory = metadata.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        session.beginTransaction();

        UserBuilder userBuilder = new UserBuilder();
        for(MasterData mData: masterData) {
            try {
                Customer customer = userBuilder.build(mData);
                session.save(customer);
            } catch (FeedBuilderException e) {
                System.out.println("unable to build feed user feed data : " + e.getMessage());
            }
        }
        session.getTransaction().commit();
        session.close();
    }
}
