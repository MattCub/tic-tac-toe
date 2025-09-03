import { render, screen } from '@testing-library/react';
import Loading from '../../../src/components/Loading/Loading';

  it('renders loading label', () => {
    render(<Loading />);
    expect(screen.getByText("Loading...")).toBeInTheDocument();
  });
