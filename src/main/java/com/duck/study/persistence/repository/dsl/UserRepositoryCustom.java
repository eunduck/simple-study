package com.duck.study.persistence.repository.dsl;

import com.duck.core.wrapper.PageContents;
import com.duck.study.dto.PageRequest;
import com.duck.study.dto.UserListDto;
import org.springframework.stereotype.Repository;

/**
 * Created by eunduck on 2022/11/03.
 */
@Repository
public interface UserRepositoryCustom {
    PageContents<UserListDto> searchUserInfos(PageRequest pageRequest);
}
