import PropTypes from "prop-types";
import Square from "../Square/Square";
import styles from "./Board.module.css";

const Board = ({ boardSummary, onSquareClick, disabled }) => {
  return (
    <div className={styles.Board}>
      {Array.from({ length: 9 }, (_, i) => (
        <Square
          key={i}
          symbol={boardSummary ? boardSummary[i] : null}
          onClick={disabled ? undefined : () => onSquareClick(i)}
          disabled={disabled}
        />
      ))}
    </div>
  );
};

Board.propTypes = {
    boardSummary: PropTypes.arrayOf(PropTypes.string),
    onSquareClick: PropTypes.func,
    disabled: PropTypes.bool
};

export default Board;
