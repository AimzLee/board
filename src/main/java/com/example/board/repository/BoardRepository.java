package com.example.board.repository;

import com.example.board.entity.Board;
import com.example.board.repository.search.SearchBoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, SearchBoardRepository {
    //연관관계가 있을 때
    @Query("SELECT b, w FROM Board b LEFT JOIN b.writer w WHERE b.bno =:bno")
    Object getBoardWithWriter(@Param("bno") Long bno);

    //연관관계가 없을 때 (양쪽의 공통된 속성을 찾아야 한다)
    @Query("SELECT b, r FROM Board b LEFT JOIN Reply r ON r.board = b WHERE b.bno = :bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);

    //여러개 (목록보기 할 때), 페이지 단위로 리턴할 때 countQuery는 필수
    @Query(value ="SELECT b, w, count(r) FROM Board b LEFT JOIN b.writer w LEFT JOIN Reply r " +
            "ON r.board = b GROUP BY b",
            countQuery ="SELECT count(b) FROM Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

    //특정정보
    @Query("SELECT b, w, count(r) FROM Board b LEFT JOIN b.writer w LEFT OUTER JOIN Reply r " +
            "ON r.board = b WHERE b.bno = :bno")
    Object getBoardByBno(@Param("bno") Long bno);


}
