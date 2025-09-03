import { renderHook, act } from '@testing-library/react';
import useApiPost from '../../src/api/useApiPost';

jest.mock('react-toastify', () => ({ toast: { error: jest.fn() } }));

describe('useApiPost', () => {
	const url = 'https://example.com/api';
	afterEach(() => {
		jest.clearAllMocks();
	});

	it('should post data and return result', async () => {
		const mockResult = { success: true };
		global.fetch = jest.fn().mockResolvedValue({
			ok: true,
			json: () => Promise.resolve(mockResult),
		});

		const { result } = renderHook(() => useApiPost(url));

		let data;
		await act(async () => {
			data = await result.current.post({ foo: 'bar' });
		});

		expect(global.fetch).toHaveBeenCalledWith(url, expect.objectContaining({
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({ foo: 'bar' })
		}));
		expect(data).toEqual(mockResult);
		expect(result.current.data).toEqual(mockResult);
		expect(result.current.error).toBeNull();
		expect(result.current.loading).toBe(false);
	});

	it('should handle error and call toast', async () => {
		const { toast } = require('react-toastify');
			global.fetch = jest.fn().mockResolvedValue({
				ok: false,
				status: 500,
				json: () => Promise.resolve({ message: 'Internal Server Error' })
			});

		const { result } = renderHook(() => useApiPost(url));

		let data;
		await act(async () => {
			data = await result.current.post({ foo: 'bar' });
		});

		expect(data).toBeNull();
		expect(result.current.data).toBeNull();
		expect(result.current.error).toBe('Error: Internal Server Error');
		expect(toast.error).toHaveBeenCalledWith('Error: Internal Server Error');
		expect(result.current.loading).toBe(false);
	});
});
