import { render, screen } from '@testing-library/react';
import Board from '../../../../../src/pages/Match/components/Board/Board';

jest.mock('../../../../../src/pages/Match/components/Square/Square', () => ({ symbol }) => <div data-testid="square">{symbol}</div>);

describe('Board', () => {
  it('renders 9 squares with correct symbols', () => {
    const boardSummary = ['X', 'O', 'X', '', '', '', 'O', 'X', 'O'];
    render(<Board boardSummary={boardSummary} />);
    const squares = screen.getAllByTestId('square');
    expect(squares).toHaveLength(9);
    expect(squares[0]).toHaveTextContent('X');
    expect(squares[1]).toHaveTextContent('O');
    expect(squares[2]).toHaveTextContent('X');
    expect(squares[3]).toHaveTextContent('');
    expect(squares[6]).toHaveTextContent('O');
  });

  it('renders empty squares if boardSummary is missing or incomplete', () => {
    render(<Board boardSummary={['X']} />);
    const squares = screen.getAllByTestId('square');
    expect(squares).toHaveLength(9);
    expect(squares[0]).toHaveTextContent('X');
    expect(squares[1]).toHaveTextContent('');
  });
});
