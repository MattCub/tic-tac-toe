import { renderHook, act, waitFor } from '@testing-library/react';
import useApiGet from '../../src/api/useApiGet';

jest.mock('react-toastify', () => ({ toast: { error: jest.fn() } }));

describe('useApiGet', () => {
	const url = 'https://example.com/api';
	afterEach(() => {
		jest.clearAllMocks();
	});

	it('should fetch data and return result', async () => {
		const mockResult = { foo: 'bar' };
		global.fetch = jest.fn().mockResolvedValue({
			ok: true,
			json: () => Promise.resolve(mockResult),
		});
			const { result } = renderHook(() => useApiGet(url));
			await act(async () => {
				await result.current.refetch();
			});
			await waitFor(() => expect(result.current.data).toEqual(mockResult));
			expect(global.fetch).toHaveBeenCalledWith(url, expect.objectContaining({ method: 'GET' }));
			expect(result.current.error).toBeNull();
			expect(result.current.loading).toBe(false);
	});

	it('should handle error and call toast', async () => {
		const { toast } = require('react-toastify');
			global.fetch = jest.fn().mockResolvedValue({
				ok: false,
				status: 404,
				json: () => Promise.resolve({ message: 'Not found' })
			});
			const { result } = renderHook(() => useApiGet(url));
			await act(async () => {
				await result.current.refetch();
			});
			await waitFor(() => expect(result.current.error).toMatch("Error"));
			expect(result.current.data).toBeNull();
			expect(toast.error).toHaveBeenCalled();
			expect(result.current.loading).toBe(false);
	});

	it('should allow manual refetch', async () => {
		const mockResult = { foo: 'bar' };
		global.fetch = jest.fn().mockResolvedValue({
			ok: true,
			json: () => Promise.resolve(mockResult),
		});
			const { result } = renderHook(() => useApiGet(url));
			await act(async () => {
				await result.current.refetch();
			});
			await waitFor(() => expect(result.current.data).toEqual(mockResult));
			global.fetch.mockClear();
			await act(async () => {
				await result.current.refetch();
			});
			await waitFor(() => expect(result.current.data).toEqual(mockResult));
			expect(global.fetch).toHaveBeenCalledWith(url, expect.objectContaining({ method: 'GET' }));
	});
});
