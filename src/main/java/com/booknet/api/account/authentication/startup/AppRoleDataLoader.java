package com.booknet.api.account.authentication.startup;

import com.booknet.api.account.authentication.repository.AppRoleRepository;
import com.booknet.api.account.authentication.model.AppRole;
import com.booknet.api.account.authentication.model.EAppRole;
import com.booknet.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRoleDataLoader implements ApplicationRunner {
    AppRoleRepository appRoleRepository;

    @Autowired
    public AppRoleDataLoader(AppRoleRepository repository) {
        this.appRoleRepository = repository;
    }

    public void run(ApplicationArguments args) {
        for (EAppRole role : EAppRole.values()) {
            Utils.log.print(this, "[Startup] add role to database", role.toString());
            if (appRoleRepository.findByName(role).isEmpty()) {
                appRoleRepository.save(new AppRole(role));
            }
            else {
                Utils.log.print(this, "[Startup] role has existed", role.toString());
            }
        }
    }
}