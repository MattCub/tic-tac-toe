const BASE_URL = import.meta.env.VITE_API_URL;
export const CREATE_MATCH_URL = `${BASE_URL}/match/create`;
export const GET_MATCH_STATUS_URL = (matchId) => `${BASE_URL}/match/${matchId}/status`;