import { useState } from "react";
import { toast } from "react-toastify";

const useApiPost = (url) => {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [data, setData] = useState(null);

  const post = async (body) => {
    setLoading(true);
    setError(null);
    setData(null);
    try {
      const response = await fetch(url, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(body),
      });
      if (!response.ok) {
        throw new Error(`Error: ${response.status}`);
      }
      const result = await response.json();
      setData(result);
      return result;
    } catch (err) {
        setError(err.message);
        toast.error(err.message);
        return null;
    } finally {
      setLoading(false);
    }
  };

  return { post, loading, error, data };
};

export default useApiPost;