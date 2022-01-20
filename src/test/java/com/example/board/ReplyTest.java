package com.example.board;

import com.example.board.entity.Board;
import com.example.board.entity.Reply;
import com.example.board.repository.ReplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ReplyTest {
    @Autowired
    ReplyRepository replyRepository;

    //@Test
    public void testListByBoard() {
        List<Reply> replyList = replyRepository.getRepliesByBoardOrderByRno( Board.builder().bno(95L).build());
        replyList.forEach(reply -> System.out.println(reply));
    }

}
