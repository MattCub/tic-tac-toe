
import { useState, useEffect } from "react";
import { useNavigate,useParams } from "react-router-dom";
import useGetMatchStatus from "../../api/useGetMatchStatus";
import Loading from "../../components/Loading/Loading";
import Header from "./components/Header/Header";
import CurrentTurn from "./components/CurrentTurn/CurrentTurn";
import Board from "./components/Board/Board";


const Match = () => {
  const navigate = useNavigate();
  const [status, setStatus] = useState(null);
  const [currentTurn, setCurrentTurn] = useState(null);
  const [winner, setWinner] = useState(null);
  const [board, setBoard] = useState(Array(9).fill(null));

  const { id } = useParams();
  const { data, loading, error, refetch } = useGetMatchStatus(id);

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
    <div>
      <Header />
      <CurrentTurn playerSimbol={currentTurn} />
      {
        loading ? <Loading /> : <Board boardSummary={board} />
      }    
    </div>
  );
};

export default Match;
