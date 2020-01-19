"""
    Board1
    Returns True
"""
board1 = [[7, 8, 0, 4, 0, 0, 1, 2, 0],
          [6, 0, 0, 0, 7, 5, 0, 0, 9],
          [0, 0, 0, 6, 0, 1, 0, 7, 8],
          [0, 0, 7, 0, 4, 0, 2, 6, 0],
          [0, 0, 1, 0, 5, 0, 9, 3, 0],
          [9, 0, 4, 0, 6, 0, 0, 0, 5],
          [0, 7, 0, 3, 0, 0, 0, 1, 2],
          [1, 2, 0, 0, 0, 7, 4, 0, 0],
          [0, 4, 9, 2, 0, 6, 0, 0, 7]]

"""
    Board2
    Returns False
"""
board2 = [[5, 3, 4, 6, 7, 8, 9, 1, 2],
          [6, 7, 2, 1, 9, 0, 3, 4, 8],
          [1, 0, 0, 3, 4, 2, 5, 6, 0],
          [8, 5, 9, 7, 6, 1, 0, 2, 0],
          [4, 2, 6, 8, 5, 3, 7, 9, 1],
          [7, 1, 3, 9, 2, 4, 8, 5, 6],
          [9, 0, 1, 5, 3, 7, 2, 1, 4],
          [2, 8, 7, 4, 1, 9, 6, 3, 5],
          [3, 0, 0, 4, 8, 1, 1, 7, 9]]


def print_board(board):
    """
        prints the board
        :param board:
    """
    for i in range(len(board)):
        if i % 3 == 0 and i != 0:
            print("------+-------+------")
        for j in range(len(board[0])):
            if j % 3 == 0 and j != 0:
                print("| {}".format(board[i][j]), end=" ")
            else:
                print(board[i][j], end=" ")
        print()


def is_whitespace(board, i, j):
    """
        Returns True if the position (j,i) on the board is empty
        :param board:
        :param i:
        :param j:
        :rtype: bool
    """
    return board[i][j] == 0


def get_whitespace(board):
    """
        Returns the position (i,j) who is empty
        If there are no more empty positions left, returns None
        :param board:
        :rtype: tuple
    """
    for i in range(len(board)):
        for j in range(len(board[i])):
            if is_whitespace(board, i, j):
                return i, j
    return None


def check_line(line):
    """
        Returns True if there are no duplicated numbers in the list, apart from 0
        Else returns False
        :param line:
        :rtype: bool
    """
    unique = []
    for el in line:
        if el in unique and el != 0:
            return False
        else:
            unique += [el]
    return True


def check_column(board, j):
    """
        Returns True if there are no duplicated numbers in the column j
        Else returns False
        :param board:
        :param j:
        :rtype: bool
    """
    column = []
    for line in board:
        column += [line[j]]
    return check_line(column)


def check_square(board, i, j):
    """
        Returns True if there are no duplicated numbers in the square which contains the element in the position (i,j)
        Else returns False
        :param board:
        :param i:
        :param j:
        :rtype: bool
    """
    box_x = j // 3
    box_y = i // 3
    square = []
    for y in range(box_y * 3, box_y * 3 + 3):
        for x in range(box_x * 3, box_x * 3 + 3):
            square += [board[y][x]]
    return check_line(square)


def valid_move(board, i, j):
    """
        Returns True if there are no duplicated numbers in line i, on column j and in square (i,j)
        Else returns False
        :param board:
        :param i:
        :param j:
        :rtype: bool
    """
    return check_line(board[i]) and check_column(board, j) and check_square(board, i, j)


def solve(board):
    """
        Returns True if there a solution for the initial puzzle
        Else returns False
        :param board:
        :rtype: bool
    """
    pos = get_whitespace(board)
    if not pos:
        return True
    i, j = pos
    for value in range(1, 10):
        board[i][j] = value
        if valid_move(board, i, j, value):
            if solve(board):
                return True
        board[i][j] = 0
    return False


if __name__ == "__main__":
    print_board(board1)
    print("\n=======Solving=======\n")
    assert solve(board1) == True
    print_board(board1)
    print("------------------------")
    print_board(board2)
    print("\n=======Solving=======\n")
    assert solve(board2) == False
    print_board(board2)
