package com.example.board;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.PageRequestDTO;
import com.example.board.dto.PageResultDTO;
import com.example.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardServiceTest {
    @Autowired
    private BoardService boardService;

    //@Test
    public void testRegister() {
        BoardDTO dto = BoardDTO.builder().title("BoardService테스트").content("Test...")
                .writerEmail("user1@test.com").build();     //존재하지 않는 이메일을 넣으면 에러
        Long bno = boardService.register(dto);
        System.out.println("삽입한 글번호: "+bno);
    }

    //@Test
    public void testList() {
        //1페이지 10개
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);
        //출력
        for (BoardDTO boardDTO : result.getDtoList()) {
            System.out.println(boardDTO);
        }
        System.out.println(result.getPageList());
    }

    //@Test
    public void testGet() {
        Long bno = 101L;
        BoardDTO boardDTO = boardService.get(bno);
        System.out.println(boardDTO);
    }

    //@Test
    public void testRemove() {
        boardService.removeWithReplies(100L);

    }

   // @Test
    public void testModify() {

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("제목 변경합니다.2")
                .content("내용 변경합니다.2")
                .build();

        boardService.modify(boardDTO);

    }

}
