package gift.dto.pageDTO;

public record PageRequestDTO(
    int page,
    int size,
    String sort
) {

}