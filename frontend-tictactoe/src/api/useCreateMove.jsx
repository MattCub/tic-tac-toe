

import useApiPost from "./useApiPost";
import { CREATE_MOVE_URL } from "../urls/urls.js";

const useCreateMove = (matchId) => {
	const { post, loading, error, data } = useApiPost(CREATE_MOVE_URL(matchId));
	const createMove = async (moveData) => {
		if (!matchId) return null;
		const response = await post(moveData);
		return response;
	};

	return { createMove, loading, error, data };
};

export default useCreateMove;
