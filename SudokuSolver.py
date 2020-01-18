'''
    :param board
'''

board = [[0, 0, 3, 0, 2, 0, 6, 0, 0],
         [9, 0, 0, 3, 0, 5, 0, 0, 1],
         [0, 0, 1, 8, 0, 6, 4, 0, 0],
         [0, 0, 8, 1, 0, 2, 9, 0, 0],
         [7, 0, 0, 0, 0, 0, 0, 0, 8],
         [0, 0, 6, 7, 0, 8, 2, 0, 0],
         [0, 0, 2, 6, 0, 9, 5, 0, 0],
         [8, 0, 0, 2, 0, 3, 0, 0, 9],
         [0, 0, 5, 0, 1, 0, 3, 0, 0]]


def print_board(board):
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
    return board[i][j] == 0


def get_whitespace(board):
    for i in range(len(board)):
        for j in range(len(board[i])):
            if is_whitespace(board, i, j):
                return i, j
    return -1


def check_line(line):
    unique = []
    for el in line:
        if el in unique and el != 0:
            return False
        else:
            unique += [el]
    return True


def check_column(board, j):
    column = []
    for line in board:
        column += [line[j]]
    print(column)
    return check_line(column)


print_board(board)
