import { useNavigate } from "react-router-dom";
import Button from "../../components/Button/Button";
import styles from "./Home.module.css";

const Home = () => {
  const navigate = useNavigate();

  const handleStartGame = () => {
    const id = Math.floor(Math.random() * 100) + 1; // Número entre 1 y 100
    navigate(`/match/${id}`);
  };

  return (
    <div className={styles.Home}>
      <h1 className={styles.Home_title}>TIC TAC TOE GAME</h1>
      <Button onClick={handleStartGame} label="Start Game" />
    </div>
  );
};

export default Home;
