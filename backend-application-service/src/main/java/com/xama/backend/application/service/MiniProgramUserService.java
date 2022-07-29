package com.xama.backend.application.service;

import com.xama.backend.domain.dto.MiniProgramUserDto;
import com.xama.backend.domain.entity.MiniProgramUser;

public interface MiniProgramUserService {

    MiniProgramUser register(MiniProgramUserDto miniProgramUserDto);
}
