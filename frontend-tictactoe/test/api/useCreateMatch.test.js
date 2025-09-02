
import { renderHook, act } from '@testing-library/react';
import useCreateMatch from '../../src/api/useCreateMatch';

jest.mock('../../src/urls/urls.js', () => ({
  CREATE_MATCH_URL: 'http://mocked-url/match/create'
}));
jest.mock('../../src/api/useApiPost', () => jest.fn());

const mockUseApiPost = require('../../src/api/useApiPost');

describe('useCreateMatch', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  it('should call post and return matchId', async () => {
    const mockPost = jest.fn().mockResolvedValue({ matchId: 15 });
    mockUseApiPost.mockReturnValue({
      post: mockPost,
      loading: false,
      error: null,
      data: { matchId: 15 },
    });

    const { result } = renderHook(() => useCreateMatch());
    let matchId;
    await act(async () => {
      matchId = await result.current.createMatch();
    });
    expect(mockPost).toHaveBeenCalled();
    expect(matchId).toBe(15);
    expect(result.current.data).toEqual({ matchId: 15 });
    expect(result.current.error).toBeNull();
    expect(result.current.loading).toBe(false);
  });

  it('should return null if no matchId in response', async () => {
    const mockPost = jest.fn().mockResolvedValue({});
    mockUseApiPost.mockReturnValue({
      post: mockPost,
      loading: false,
      error: null,
      data: {},
    });

    const { result } = renderHook(() => useCreateMatch());
    let matchId;
    await act(async () => {
      matchId = await result.current.createMatch();
    });
    expect(matchId).toBeNull();
  });

  it('should expose loading and error from useApiPost', () => {
    mockUseApiPost.mockReturnValue({
      post: jest.fn(),
      loading: true,
      error: 'error',
      data: null,
    });
    const { result } = renderHook(() => useCreateMatch());
    expect(result.current.loading).toBe(true);
    expect(result.current.error).toBe('error');
  });
});
