import { renderHook } from '@testing-library/react';
import useGetMatchStatus from '../../src/api/useGetMatchStatus';

jest.mock('../../src/urls/urls.js', () => ({
	GET_MATCH_STATUS_URL: (matchId) => `http://localhost:8080/match/${matchId}/status`
}));
jest.mock('../../src/api/useApiGet', () => jest.fn());
const mockUseApiGet = require('../../src/api/useApiGet');

describe('useGetMatchStatus', () => {
	afterEach(() => {
		jest.clearAllMocks();
	});

	it('should call useApiGet with correct url and return data', () => {
		const fakeData = { status: 'CREATED', currentTurn: 'X', winner: null, overall: [['-','-','-'],['-','-','-'],['-','-','-']] };
		mockUseApiGet.mockReturnValue({ data: fakeData, loading: false, error: null, refetch: jest.fn() });
		const { result } = renderHook(() => useGetMatchStatus('2'));
		expect(mockUseApiGet).toHaveBeenCalledWith('http://localhost:8080/match/2/status');
		expect(result.current.data).toEqual(fakeData);
		expect(result.current.loading).toBe(false);
		expect(result.current.error).toBeNull();
	});
});
