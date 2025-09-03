import styles from './NotFound.module.css';

const NotFound = () => {
  return (
    <div className={styles.NotFound}>
      <h2>404 - Not Found</h2>
      <p>The page you are looking for does not exist.</p>
    </div>
  );
};

export default NotFound;