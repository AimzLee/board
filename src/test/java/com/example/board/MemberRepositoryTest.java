package com.example.board;

import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.entity.Reply;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.MemberRepository;
import com.example.board.repository.ReplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    ReplyRepository replyRepository;

    //@Test
    public void insertMembers() {
        IntStream.rangeClosed(1,100).forEach(i->{
            Member member = Member.builder().email("user"+i+"@test.com").password("1234").name("USER"+i).build();
            memberRepository.save(member);
        });
    }

    //@Test
    public void insertBoard() {
        IntStream.rangeClosed(1,100).forEach(i -> {
            Member member = Member.builder().email("user"+i +"@test.com").build();
            Board board = Board.builder().title("Title..."+i).content("Content...." + i).writer(member).build();
            boardRepository.save(board);
        });
    }

    //@Test
    public void insertReply() {
        IntStream.rangeClosed(301, 500).forEach(i -> {
            //1부터 100까지의 임의의 번호
            long bno  = (long)(Math.random() * 100) + 1;
            Board board = Board.builder().bno(bno).build();
            Reply reply = Reply.builder()
                    .text("Reply......." +i)
                    .board(board)
                    .replyer("guest")
                    .build();
            replyRepository.save(reply);
        });
    }

    //@Transactional LAZY 타입일때 추가
    //@Test
    public void eagerLoading() {
        Optional<Board> board = boardRepository.findById(100L);
        if(board.isPresent()){
            System.out.println(board.get());
            System.out.println(board.get().getWriter());
        }
    }

    //@Test
    public void readReply() {
        Optional<Reply> result = replyRepository.findById(1L);
        Reply reply = result.get();

        System.out.println(reply);
        System.out.println(reply.getBoard());
    }

    //@Test
    public void testReadWithWriter() {
        Object result = boardRepository.getBoardWithWriter(100L);
        Object[] arr = (Object[])result;
        System.out.println("-------------------------------------------------- ");
        System.out.println(Arrays.toString(arr));
    }

    //@Test
    public void testGetBoardWithReply() {
        List<Object[]> result = boardRepository.getBoardWithReply(87L);
        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }

    //@Test
    public void testWithReplyCount(){
        Pageable pageable = PageRequest.of(0,15, Sort.by("bno").descending());

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);
        result.get().forEach(row -> {
            Object[] arr = (Object[])row;
            System.out.println(Arrays.toString(arr));
        });
    }

    @Test
    public void testRead2(){
        Object result = boardRepository.getBoardByBno(87L);
        Object[] arr = (Object[])result;
        System.out.println(Arrays.toString(arr));
    }






}
