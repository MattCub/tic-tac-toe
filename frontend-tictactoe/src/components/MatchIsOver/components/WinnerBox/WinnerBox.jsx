import PropTypes from "prop-types";
import Mark from "../../../Mark/Mark";
import styles from "./WinnerBox.module.css";

const WinnerBox = ({ winner }) => {
  return (
    <div className={styles.WinnerBox}>
      <Mark symbol={winner} />
    </div>
  );
};

WinnerBox.propTypes = {
  winner: PropTypes.string
};

export default WinnerBox;