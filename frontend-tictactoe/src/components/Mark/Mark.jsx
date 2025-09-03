import PropTypes from "prop-types";
import Circle from "../../assets/Circle";
import Cross from "../../assets/Cross";

const circleSymbol = "O";
const crossSymbol = "X";

const Mark = ({ symbol }) => {
  const renderMark = () => {
    if (symbol?.toUpperCase() === circleSymbol) {
      return <Circle />;
    }
    if (symbol?.toUpperCase() === crossSymbol) {
      return <Cross />;
    }
    return null;
  };

  return (
    <div>
      {renderMark()}
    </div>
  );
};

Mark.propTypes = {
  symbol: PropTypes.string
};

export default Mark;