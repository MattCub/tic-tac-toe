import PropTypes from "prop-types";
import WinnerBox from "./components/WinnerBox/WinnerBox";import { useNavigate } from "react-router-dom";
import useCreateMatch from "../../api/useCreateMatch";
import Button from "../Button/Button";
import styles from "./MatchIsOver.module.css";

const MatchIsOver = ({ winner }) => {
  const navigate = useNavigate();
  const { createMatch } = useCreateMatch();

  const handleStartGame = async () => {
    const matchId = await createMatch();
    if (matchId) {
      navigate(`/match/${matchId}`);
    }
  };
  return (
    <div className={styles.MatchIsOver}>
      {winner ? (
        <>
          <h2>Player {winner} wins!</h2>
          <WinnerBox winner={winner} />
          <Button onClick={handleStartGame} label={"Play again"} />
        </>
      ) : (
      <>
        <h2>It's a draw!</h2>
        <Button onClick={handleStartGame} label={"Play again"} />
      </>
      )}
    </div>
  );
};

MatchIsOver.propTypes = {
  winner: PropTypes.string
};


export default MatchIsOver;