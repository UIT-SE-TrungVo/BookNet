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
        var newGuild = new GuildModel(id, name);
        newGuild.setDescription(description);
        Utils.log.print(this, "Add guild 1", id, name);
        guildRepository.save(newGuild);
    }

    private void addGuild2() {
        var id = IdGenerator.createNew(IdPrefix.GUILD);
        var name = "Hội những người yêu Chó";
        var description = "Tình yêu tươi đẹp, tình yêu màu hồng....là tình yêu chó";
        var newGuild = new GuildModel(id, name);
        newGuild.setDescription(description);
        Utils.log.print(this, "Add guild 2", id, name);
        guildRepository.save(newGuild);
    }
}