package com.example.board.service;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.PageRequestDTO;
import com.example.board.dto.PageResultDTO;
import com.example.board.entity.Board;
import com.example.board.entity.Member;

public interface BoardService {

    public Long register(BoardDTO dto);
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
    public BoardDTO get(Long bno);
    public void removeWithReplies(Long bno);
    public void modify(BoardDTO boardDTO);

    default Board dtoToEntity(BoardDTO dto) {
        Member member = Member.builder().email(dto.getWriterEmail()).build();
        Board board = Board.builder().bno(dto.getBno()).title(dto.getTitle()).content(dto.getContent())
                .writer(member).build();
        return board;
    }

    default BoardDTO entityToDTO(Board board, Member member, Long replyCount) {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue()) //int로 처리하도록
                .build();

        return boardDTO;
    }


}
