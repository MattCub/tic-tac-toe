import PropTypes from "prop-types";
import Mark from "../../../../components/Mark/Mark";
import styles from "./Square.module.css";

const Square = ({ symbol, onClick }) => {
	return (
		<div className={styles.Square} onClick={onClick}>
            <Mark symbol={symbol} />
		</div>
	);
};

Square.propTypes = {
	symbol: PropTypes.string,
	onClick: PropTypes.func
};

export default Square;
