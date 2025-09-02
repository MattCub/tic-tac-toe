import PropTypes from "prop-types";
import styles from "./Button.module.css";

const Button = ({ onClick, label }) => {
  return (
    <button className={styles.Button} onClick={onClick}>
      {label}
    </button>
  );
};

Button.propTypes = {
  onClick: PropTypes.func.isRequired,
  label: PropTypes.string.isRequired,
};

export default Button;
