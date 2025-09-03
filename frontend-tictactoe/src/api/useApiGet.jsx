
import { useState, useEffect, useCallback } from "react";
import { toast } from "react-toastify";

const useApiGet = (url, options = {}) => {
	const [loading, setLoading] = useState(false);
	const [error, setError] = useState(null);
	const [data, setData] = useState(null);

	const fetchData = useCallback(async () => {
		setLoading(true);
		setError(null);
		setData(null);
		try {
			const response = await fetch(url, {
				method: "GET",
				headers: {
					"Content-Type": "application/json",
					...options.headers,
				},
				...options,
			});
			if (!response.ok) {
			    const error = await response.json();
				throw new Error(`Error: ${error.message}`);
			}
			const result = await response.json();
			setData(result);
			return result;
		} catch (err) {
            debugger;
			setError(err.message);
			toast.error(err.message);
			return null;
		} finally {
			setLoading(false);
		}
	}, [url, JSON.stringify(options)]);


	return { data, loading, error, refetch: fetchData };
};

export default useApiGet;
