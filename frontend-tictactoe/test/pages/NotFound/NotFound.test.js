import { render, screen } from '@testing-library/react';
import NotFound from "../../../src/pages/NotFound/NotFound";

  it('renders title and description', () => {
    render(<NotFound />);
    expect(screen.getByText("404 - Not Found")).toBeInTheDocument();
    expect(screen.getByText("The page you are looking for does not exist.")).toBeInTheDocument();
  });
