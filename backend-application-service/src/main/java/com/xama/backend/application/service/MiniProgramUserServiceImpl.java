package com.xama.backend.application.service;

//import com.xama.backend.dao.repository.MiniProgramUserRepository;
import com.xama.backend.dao.repository.MiniProgramUserRepository;
import com.xama.backend.domain.dto.MiniProgramUserDto;
import com.xama.backend.domain.entity.MiniProgramUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiniProgramUserServiceImpl implements MiniProgramUserService{

    private final MiniProgramUserRepository miniProgramUserRepository;

    public MiniProgramUserServiceImpl(MiniProgramUserRepository miniProgramUserRepository) {
        this.miniProgramUserRepository = miniProgramUserRepository;
    }

    @Override
    public MiniProgramUser register(MiniProgramUserDto miniProgramUserDto) {
        MiniProgramUser miniProgramUser = MiniProgramUser.register(miniProgramUserDto);

        miniProgramUserRepository.save(miniProgramUser);
        return miniProgramUser;
    }
}
