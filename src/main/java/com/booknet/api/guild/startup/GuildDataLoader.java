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

        this.addGuild1();
        this.addGuild2();
    }

    private void addGuild1() {
        var id = IdGenerator.createNew(IdPrefix.GUILD);
        var name = "Hội những người yêu Hoa";
        var description = "Tình yêu tươi đẹp, tình yêu màu hồng....là tình yêu hoa";
        var imageUrl = "https://scontent.fsgn2-1.fna.fbcdn.net/v/t39.30808-6/285711179_5617138174986413_6498973096504769786_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=e3f864&_nc_ohc=lIQK5lQ_j6MAX-1oyLA&tn=xvG496jA7qoqR9u7&_nc_ht=scontent.fsgn2-1.fna&oh=00_AT9toqA5tfFa4kQHZuVCANWELtf_tIf1YRz19MrKRIqZ9Q&oe=62A55701";
        var newGuild = new GuildModel(id, name, imageUrl);
        newGuild.setDescription(description);
        Utils.log.print(this, "Add guild 1", id, name);
        guildRepository.save(newGuild);
    }

    private void addGuild2() {
        var id = IdGenerator.createNew(IdPrefix.GUILD);
        var name = "Hội những người yêu Chó";
        var description = "Tình yêu tươi đẹp, tình yêu màu hồng....là tình yêu chó";
        var imageUrl = "https://scontent.fsgn2-1.fna.fbcdn.net/v/t39.30808-6/285711179_5617138174986413_6498973096504769786_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=e3f864&_nc_ohc=lIQK5lQ_j6MAX-1oyLA&tn=xvG496jA7qoqR9u7&_nc_ht=scontent.fsgn2-1.fna&oh=00_AT9toqA5tfFa4kQHZuVCANWELtf_tIf1YRz19MrKRIqZ9Q&oe=62A55701";
        var newGuild = new GuildModel(id, name, imageUrl);
        newGuild.setDescription(description);
        Utils.log.print(this, "Add guild 2", id, name);
        guildRepository.save(newGuild);
    }
}