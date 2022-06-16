package com.booknet.api.guild.startup;

import com.booknet.api.guild.model.GuildModel;
import com.booknet.api.guild.repository.GuildRepository;
import com.booknet.constants.IdPrefix;
import com.booknet.utils.IdGenerator;
import com.booknet.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class GuildDataLoader implements ApplicationRunner {
    GuildRepository guildRepository;

    @Autowired
    public GuildDataLoader(GuildRepository repository) {
        guildRepository = repository;
    }

    public void run(ApplicationArguments args) {
        if (guildRepository.count() != 0) return;
        guildRepository.deleteAll();

        this.addGuild1();
        this.addGuild2();
        this.addGuild3();
        this.addGuild4();
        this.addGuild5();
        this.addGuild6();
        this.addGuild7();
        this.addGuild8();
        this.addGuild9();
        this.addGuild10();
    }

    private void addGuild1() {
        var id = IdGenerator.createNew(IdPrefix.GUILD);
        var name = "WeBook";
        var description = "A book discussion club is a group of people who meet to discuss a book or books that they have read and express their opinions, likes, dislikes, etc.";
        var imageUrl = "https://i.pinimg.com/564x/d5/b2/31/d5b2314b8ef86b8a7949c3b209e0aaba.jpg";
        var newGuild = new GuildModel(id, name, imageUrl, description);
        guildRepository.save(newGuild);
    }

    private void addGuild2() {
        var id = IdGenerator.createNew(IdPrefix.GUILD);
        var name = "Reading is growing";
        var description = "A book discussion club is a group of people who meet to discuss a book or books that they have read and express their opinions, likes, dislikes, etc.";
        var imageUrl = "https://i.pinimg.com/564x/94/3b/3e/943b3e7ff25ee56fa87a1bf8e62aaafe.jpg";
        var newGuild = new GuildModel(id, name, imageUrl, description);
        guildRepository.save(newGuild);
    }

    private void addGuild3() {
        var id = IdGenerator.createNew(IdPrefix.GUILD);
        var name = "WeRead";
        var description = "A book discussion club is a group of people who meet to discuss a book or books that they have read and express their opinions, likes, dislikes, etc.";
        var imageUrl = "https://i.pinimg.com/564x/74/3c/13/743c13f3dd1bd7531c41526c14e4582b.jpg";
        var newGuild = new GuildModel(id, name, imageUrl, description);
        guildRepository.save(newGuild);
    }

    private void addGuild4() {
        var id = IdGenerator.createNew(IdPrefix.GUILD);
        var name = "Goodreads";
        var description = "A book discussion club is a group of people who meet to discuss a book or books that they have read and express their opinions, likes, dislikes, etc.";
        var imageUrl = "https://i.pinimg.com/564x/ee/8e/90/ee8e90420e2c5120ba63e81f419da1cb.jpg";
        var newGuild = new GuildModel(id, name, imageUrl, description);
        guildRepository.save(newGuild);
    }

    private void addGuild5() {
        var id = IdGenerator.createNew(IdPrefix.GUILD);
        var name = "Guild to read";
        var description = "A book discussion club is a group of people who meet to discuss a book or books that they have read and express their opinions, likes, dislikes, etc.";
        var imageUrl = "https://i.pinimg.com/564x/ee/8e/90/ee8e90420e2c5120ba63e81f419da1cb.jpg";
        var newGuild = new GuildModel(id, name, imageUrl, description);
        guildRepository.save(newGuild);
    }

    private void addGuild6() {
        var id = IdGenerator.createNew(IdPrefix.GUILD);
        var name = "ReadingGuild";
        var description = "A book discussion club is a group of people who meet to discuss a book or books that they have read and express their opinions, likes, dislikes, etc.";
        var imageUrl = "https://images.pexels.com/photos/2079451/pexels-photo-2079451.jpeg?auto=compress&cs=tinysrgb&w=800";
        var newGuild = new GuildModel(id, name, imageUrl, description);
        guildRepository.save(newGuild);
    }

    private void addGuild7() {
        var id = IdGenerator.createNew(IdPrefix.GUILD);
        var name = "Read&Read";
        var description = "A book discussion club is a group of people who meet to discuss a book or books that they have read and express their opinions, likes, dislikes, etc.";
        var imageUrl = "https://images.pexels.com/photos/3166839/pexels-photo-3166839.jpeg?auto=compress&cs=tinysrgb&w=800";
        var newGuild = new GuildModel(id, name, imageUrl, description);
        guildRepository.save(newGuild);
    }

    private void addGuild8() {
        var id = IdGenerator.createNew(IdPrefix.GUILD);
        var name = "9Reading";
        var description = "A book discussion club is a group of people who meet to discuss a book or books that they have read and express their opinions, likes, dislikes, etc.";
        var imageUrl = "https://images.pexels.com/photos/616849/pexels-photo-616849.jpeg?auto=compress&cs=tinysrgb&w=800";
        var newGuild = new GuildModel(id, name, imageUrl, description);
        guildRepository.save(newGuild);
    }

    private void addGuild9() {
        var id = IdGenerator.createNew(IdPrefix.GUILD);
        var name = "Books & Life";
        var description = "A book discussion club is a group of people who meet to discuss a book or books that they have read and express their opinions, likes, dislikes, etc.";
        var imageUrl = "https://images.pexels.com/photos/2846814/pexels-photo-2846814.jpeg?auto=compress&cs=tinysrgb&w=800";
        var newGuild = new GuildModel(id, name, imageUrl, description);
        guildRepository.save(newGuild);
    }

    private void addGuild10() {
        var id = IdGenerator.createNew(IdPrefix.GUILD);
        var name = "Wanna Read";
        var description = "A book discussion club is a group of people who meet to discuss a book or books that they have read and express their opinions, likes, dislikes, etc.";
        var imageUrl = "https://images.pexels.com/photos/1684151/pexels-photo-1684151.jpeg?auto=compress&cs=tinysrgb&w=800";
        var newGuild = new GuildModel(id, name, imageUrl, description);
        guildRepository.save(newGuild);
    }
}