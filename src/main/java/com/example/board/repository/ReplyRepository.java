package com.example.board.repository;

import com.example.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Modifying
    @Query("delete from Reply r where r.board.bno = :bno")  //연결된 데이터 삭제하도록 설정
    public void deleteByBno(Long bno);
}
