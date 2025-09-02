import { useNavigate } from "react-router-dom";
import Button from "../../components/Button/Button";
import useCreateMatch from "../../api/useCreateMatch";
import styles from "./Home.module.css";

const Home = () => {
  const navigate = useNavigate();
  const { createMatch, loading } = useCreateMatch();

  const handleStartGame = async () => {
    const matchId = await createMatch();
    if (matchId) {
      navigate(`/match/${matchId}`);
    }
  };

  return (
    <div className={styles.Home}>
      <h1 className={styles.Home_title}>TIC TAC TOE GAME</h1>
      <Button onClick={handleStartGame} label={"Start Game"} />
    </div>
  );
};

export default Home;