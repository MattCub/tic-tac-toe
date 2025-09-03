
import { render, screen } from '@testing-library/react';
import CurrentTurn from '../../../../../src/pages/Match/components/CurrentTurn/CurrentTurn';

describe('CurrentTurn', () => {
	it('renders the title', () => {
		render(<CurrentTurn playerSimbol="X" />);
		expect(screen.getByText('Turn')).toBeInTheDocument();
	});
});
