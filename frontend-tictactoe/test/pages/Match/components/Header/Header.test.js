import { render, screen } from '@testing-library/react';
import Header from '../../../../../src/pages/Match/components/Header/Header';

  it('renders Header title', () => {
    render(<Header />);
    expect(screen.getByText("TIC TAC TOE")).toBeInTheDocument();
  });
