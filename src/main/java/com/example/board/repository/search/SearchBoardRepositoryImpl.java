package com.example.board.repository.search;

import com.example.board.entity.Board;
import com.example.board.entity.QBoard;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository{
    public SearchBoardRepositoryImpl(){
        super(Board.class);
    }

    @Override
    public Board search() {
        log.info("search에서 메서드 호출");
        QBoard board = QBoard.board;
        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.select(board).where(board.bno.eq(1L));
        List<Board> result = jpqlQuery.fetch();
        System.out.println(result);
        return null;
    }
}
