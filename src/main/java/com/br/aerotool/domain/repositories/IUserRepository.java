package com.br.aerotool.domain.repositories;

import com.br.aerotool.application.interfaces.user.ICreateUser;
import com.br.aerotool.application.interfaces.user.IDeleteUser;
import com.br.aerotool.application.interfaces.user.IReadUser;
import com.br.aerotool.application.interfaces.user.IUpdateUser;

public interface IUserRepository extends ICreateUser, IDeleteUser, IUpdateUser, IReadUser {
}
