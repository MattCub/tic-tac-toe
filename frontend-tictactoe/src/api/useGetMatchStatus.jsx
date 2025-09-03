import useApiGet from './useApiGet';
import { GET_MATCH_STATUS_URL } from '../urls/urls.js';

const useGetMatchStatus = (matchId) => {
	const { data, loading, error, refetch } = useApiGet(GET_MATCH_STATUS_URL(matchId));
	return { data, loading, error, refetch };
};

export default useGetMatchStatus;
