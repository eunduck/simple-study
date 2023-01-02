package com.duck.study.persistence.repository.dsl;

import com.duck.core.code.SearchCondition;
import com.duck.core.wrapper.PageContents;
import com.duck.study.dto.PageRequest;
import com.duck.study.dto.UserListDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.List;

import static com.duck.study.persistence.entity.QUserEntity.userEntity;
import static com.duck.study.persistence.entity.QOrderEntity.orderEntity;

/**
 * Created by eunduck on 2022/11/03.
 */
@Log4j2
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public PageContents<UserListDto> searchUserInfos(PageRequest searchInfo) {
        QueryResults<UserListDto> results = jpaQueryFactory
                .select(Projections.fields(UserListDto.class,
                        userEntity.id
                        ,userEntity.name
                        ,userEntity.email
                        , orderEntity.createdAt.max().as("lastOrderAt")
                ))
                .from(userEntity)
                .leftJoin(orderEntity).on(userEntity.id.eq(orderEntity.user.id))
                .where(searchBuilder(searchInfo))
                .offset(searchInfo.getOffset())
                .limit(searchInfo.getPageSize())
                .groupBy(userEntity.id)
                .orderBy(userEntity.id.asc())
                .fetchResults();

        List<UserListDto> contents = results.getResults();
        long total = results.getTotal();

        return new PageContents<>(contents, searchInfo, total);
    }

    private BooleanBuilder searchBuilder(PageRequest request) {
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.isEmpty(request.getWord()))
            return builder;

        if (request.getCondition().equals(SearchCondition.USER_NAME))
            builder.and(userEntity.name.like("%"+request.getWord()+"%"));

        if (request.getCondition().equals(SearchCondition.USER_EMAIL))
            builder.and(userEntity.email.like("%"+request.getWord()+"%"));

        return builder;
    }
}
