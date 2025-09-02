import { render, screen, fireEvent } from '@testing-library/react';
import Home from '../../../src/pages/Home/Home';
import React from 'react';

jest.mock('react-router-dom', () => ({
  useNavigate: jest.fn()
}));
jest.mock('../../../src/api/useCreateMatch', () => jest.fn());
jest.mock('../../../src/components/Button/Button', () => (props) => (
  <button onClick={props.onClick}>{props.label}</button>
));

const mockUseNavigate = require('react-router-dom').useNavigate;
const mockUseCreateMatch = require('../../../src/api/useCreateMatch');

describe('Home', () => {
  beforeEach(() => {
    jest.clearAllMocks();
  });

  it('renders title and button', () => {
    mockUseCreateMatch.mockReturnValue({ createMatch: jest.fn(), loading: false });
    render(<Home />);
    expect(screen.getByText("TIC TAC TOE GAME")).toBeInTheDocument();
    expect(screen.getByRole('button', { name: "Start Game" })).toBeInTheDocument();
  });

  it('calls createMatch and navigates on success', async () => {
    const mockNavigate = jest.fn();
    mockUseNavigate.mockReturnValue(mockNavigate);
    const mockCreateMatch = jest.fn().mockResolvedValue(15);
    mockUseCreateMatch.mockReturnValue({ createMatch: mockCreateMatch, loading: false });
    render(<Home />);
    fireEvent.click(screen.getByRole('button', { name: "Start Game" }));
    await Promise.resolve();
    expect(mockCreateMatch).toHaveBeenCalled();
    expect(mockNavigate).toHaveBeenCalledWith('/match/15');
  });

  it('should not navigate if createMatch returns null', async () => {
    const mockNavigate = jest.fn();
    mockUseNavigate.mockReturnValue(mockNavigate);
    const mockCreateMatch = jest.fn().mockResolvedValue(null);
    mockUseCreateMatch.mockReturnValue({ createMatch: mockCreateMatch, loading: false });
    render(<Home />);
    fireEvent.click(screen.getByRole('button', { name: "Start Game" }));
    await Promise.resolve();
    expect(mockCreateMatch).toHaveBeenCalled();
    expect(mockNavigate).not.toHaveBeenCalled();
  });
});
