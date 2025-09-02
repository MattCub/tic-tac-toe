import useApiPost from "./useApiPost";
import { CREATE_MATCH_URL } from "../urls/urls.js";

const useCreateMatch = () => {
  const { post, loading, error, data } = useApiPost(CREATE_MATCH_URL);

  const createMatch = async () => {
    const response = await post();
    return response?.matchId ?? null;
  };

  return { createMatch, loading, error, data };
};

export default useCreateMatch;