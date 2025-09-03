import PropTypes from "prop-types";
import Mark from "../../../../components/Mark/Mark";
import styles from "./Square.module.css";

const Square = ({ symbol, onClick, disabled }) => {
	const isDisabled = symbol !== null && symbol !== "-" || disabled;
	return (
		<div
			className={isDisabled ? `${styles.Square} ${styles.disabled}` : styles.Square}
			onClick={isDisabled ? undefined : onClick}
			style={isDisabled ? { pointerEvents: 'none' } : {}}
		>
			<Mark symbol={symbol} />
		</div>
	);
};

Square.propTypes = {
	symbol: PropTypes.string,
	onClick: PropTypes.func,
	disabled: PropTypes.bool
};

export default Square;
