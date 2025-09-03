import PropTypes from "prop-types";
import Mark from "../../../../components/Mark/Mark";
import styles from "./CurrentTurn.module.css";

const CurrentTurn = ({ playerSimbol }) => (
	<div className={styles.CurrentTurn}>
		<h3>Turn</h3>
		<Mark symbol={playerSimbol} />
	</div>
);

CurrentTurn.propTypes = {
  playerSimbol: PropTypes.string
};

export default CurrentTurn;