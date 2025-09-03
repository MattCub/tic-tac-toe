import PropTypes from "prop-types";
import Square from "../Square/Square";
import styles from "./Board.module.css";

const Board = ({ boardSummary }) => {
  return (
    <div className={styles.Board}>
      {Array.from({ length: 9 }, (_, i) => (
        <Square
          key={i}
          symbol={boardSummary ? boardSummary[i] : null}
          onClick={() => console.log("Clicked")}
        />
      ))}
    </div>
  );
};

Board.propTypes = {
    boardSummary: PropTypes.arrayOf(PropTypes.string)
};

export default Board;
