
import { useState, useEffect } from "react";
import { useNavigate,useParams } from "react-router-dom";
import Loading from "../../components/Loading/Loading";
import Header from "./components/Header/Header";
import CurrentTurn from "./components/CurrentTurn/CurrentTurn";
import Board from "./components/Board/Board";
import useCreateMove from "../../api/useCreateMove";
import useGetMatchStatus from "../../api/useGetMatchStatus";
import MatchIsOver from "../../components/MatchIsOver/MatchIsOver";
import styles from "./Match.module.css";



const Match = () => {
  const navigate = useNavigate();
  const [status, setStatus] = useState(null);
  const [currentTurn, setCurrentTurn] = useState(null);
  const [winner, setWinner] = useState(null);
  const [board, setBoard] = useState(Array(9).fill(null));

  const { id } = useParams();
  const { data, loading, error, refetch } = useGetMatchStatus(id);
  const { createMove, loading: creating, error: createError } = useCreateMove(id);

  const onSquareClick = async (index) => {
    if (creating || !currentTurn) return;
    const response = await createMove({ playerId: currentTurn, square: { x: Math.floor(index / 3) + 1, y: index % 3 + 1 } });
    if (response) {
      setStatus(response.status);
      setWinner(response.winner);
      setCurrentTurn(response.currentTurn);
      setBoard(response.overall?.flat());
    }
  }

  useEffect(() => {
    if (id) {
      refetch();
    }
  }, [id, refetch]);

  useEffect(() => {
    if (!data && !loading && !error) return;
    if (!data && !loading && error) navigate("/404");
    if (data) {
      setStatus(data.status);
      setCurrentTurn(data.currentTurn);
      setWinner(data.winner);
      setBoard(data.overall?.flat());
    }
  }, [data, loading, error, navigate]);
  return (
    <div className={styles.Match}>
      <Header />
      <CurrentTurn playerSimbol={currentTurn} />
      {
        loading ? <Loading />
        : status === "ENDED" ? <MatchIsOver winner={winner} />
        : <Board boardSummary={board} onSquareClick={onSquareClick} disabled={creating} />
      }    
    </div>
  );
};

export default Match;
