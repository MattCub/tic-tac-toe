
import { renderHook, act } from '@testing-library/react';

import useCreateMove from '../../src/api/useCreateMove';

jest.mock('../../src/urls/urls.js', () => ({
  CREATE_MOVE_URL: (matchId) => `http://mocked-url/match/${matchId}/move`
}));

jest.mock('../../src/api/useApiPost', () => jest.fn());
const mockUseApiPost = require('../../src/api/useApiPost');

const matchId = 14;
const moveData = {
  playerId: "O",
  square: { x: 2, y: 3 },
};
const apiResponse = {
  moveId: 4,
  status: "IN_PROGRESS",
  winner: null,
  overall: [
    ["X", "X", "-"],
    ["-", "O", "O"],
    ["-", "-", "-"],
  ],
};

describe('useCreateMove', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  it('should call post with correct data and return response', async () => {
    const mockPost = jest.fn().mockResolvedValue(apiResponse);
    mockUseApiPost.mockReturnValue({
      post: mockPost,
      loading: false,
      error: null,
      data: apiResponse,
    });

    const { result } = renderHook(() => useCreateMove(matchId));
    let response;
    await act(async () => {
      response = await result.current.createMove(moveData);
    });
    expect(mockPost).toHaveBeenCalledWith(moveData);
    expect(response).toEqual(apiResponse);
    expect(result.current.data).toEqual(apiResponse);
    expect(result.current.error).toBeNull();
    expect(result.current.loading).toBe(false);
  });

  it('should return null if no matchId is provided', async () => {
    const mockPost = jest.fn();
    mockUseApiPost.mockReturnValue({
      post: mockPost,
      loading: false,
      error: null,
      data: null,
    });

    const { result } = renderHook(() => useCreateMove(null));
    let response;
    await act(async () => {
      response = await result.current.createMove(moveData);
    });
    expect(response).toBeNull();
    expect(mockPost).not.toHaveBeenCalled();
  });

  it('should expose loading and error from useApiPost', () => {
    mockUseApiPost.mockReturnValue({
      post: jest.fn(),
      loading: true,
      error: 'error',
      data: null,
    });
    const { result } = renderHook(() => useCreateMove(matchId));
    expect(result.current.loading).toBe(true);
    expect(result.current.error).toBe('error');
  });
});
